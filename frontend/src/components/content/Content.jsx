// Dependencies
import React from 'react';
import 'whatwg-fetch'

// Styles
import './Content.scss';

import ManageEquipment from './../manageequipment/ManageEquipment.jsx';

class Content extends React.Component {
    constructor(props) {
        super(props);

        if (!localStorage.getItem('token')) {
            this.props.history.push('/');
        }
        this.state = {
            equipments: [],
            showManage: false
        };

        this.closeManage = this.closeManage.bind(this);
        this.componentDidMount = this.componentDidMount.bind(this);
        this.logout = this.logout.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:8080/equipment', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `bearer ${localStorage.getItem('token')}`
            }
        }).then((response) => {
            response.json()
                .then(it => {
                    this.setState({
                        equipments: it.results
                    })
                });
        })
    }

    openManage(equipment) {
        this.setState({showManage: true, equipment: equipment})
    };

    closeManage() {
        this.setState({showManage: false});
    }

    remove(id) {
        fetch(`http://localhost:8080/equipment/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `bearer ${localStorage.getItem('token')}`
            }
        }).then(() => {
            this.componentDidMount();
        });

    }

    logout() {
        localStorage.setItem('Authorization', undefined);
        this.props.history.push('/');
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <table className="table table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Edit</th>
                                <th>Remove</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.equipments ? this.state.equipments.map((it, index) => {
                                return (
                                    <tr key={index}>
                                        <th scope="row">{index}</th>
                                        <td>{it.name}</td>
                                        <td>
                                            <button type="button" className="btn btn-warning"
                                                    onClick={this.openManage.bind(this, it)}>
                                                Edit
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" onClick={this.remove.bind(this, it.id)}
                                                    className="btn btn-danger">
                                                Remove
                                            </button>
                                        </td>
                                    </tr>
                                );
                            }) : ''}
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-2">
                        <button onClick={this.openManage.bind(this, undefined)} type="button"
                                className="btn btn-primary">Add equipment
                        </button>
                        <button onClick={this.logout} type="button"
                                className="btn btn-primary">Log out
                        </button>
                    </div>
                </div>
                <div id="manage">
                    {this.state.showManage ?
                        <ManageEquipment equipment={this.state.equipment} update={this.componentDidMount}
                                         close={this.closeManage}/> : ''}
                </div>
            </div>
        );
    }
}

export default Content;