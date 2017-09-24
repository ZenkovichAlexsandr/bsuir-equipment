// Dependencies
import React from 'react';
import 'whatwg-fetch'

// Styles
import './ManageEquipment.scss';

class ManageEquipment extends React.Component {
    constructor(props) {
        super(props);
        this.state = {name: this.props.equipment ? this.props.equipment.name : ''};

        this.save = this.save.bind(this);
        this.updateName = this.updateName.bind(this);
    }

    save() {
        if (!this.state.name) {
            return;
        }
        const url = this.props.equipment ? `http://localhost:8080/equipment/${this.props.equipment.id}` :
            'http://localhost:8080/equipment';
        const method = this.props.equipment ? `PUT` : 'POST';

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `bearer ${localStorage.getItem('token')}`
            },
            body: JSON.stringify({
                name: this.state.name
            })
        }).then(response => {
            response.json()
                .then(() => {
                    this.props.update();
                    this.props.close();
                });
        })
    }

    updateName(event) {
        this.setState({name: event.target.value});
    }

    render() {
        return (
            <div className="modal fade in" style={{display: 'block'}} role="dialog">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Manage equipment</h5>
                            <button type="button" className="close" onClick={this.props.close}>
                                <span>&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <form>
                                <div className="form-group">
                                    <label className="form-control-label">Name:</label>
                                    <input value={this.state.name} onChange={this.updateName} type="text"
                                           className="form-control"/>
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" onClick={this.props.close}>Close
                            </button>
                            <button type="button" className="btn btn-primary" onClick={this.save}>Save</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ManageEquipment;