// Dependencies
import React from 'react';
import 'whatwg-fetch'

// Styles
import './ManageEquipment.scss';

class ManageEquipment extends React.Component {
    constructor(props) {
        super(props);
        const e = this.props.equipment || {};
        this.state = {
            name: e.name,
            details: e.details,
            description: e.description,
            status: e.status,
            price: e.price
        };

        this.save = this.save.bind(this);
        this.updateName = this.updateName.bind(this);
        this.updateDetails = this.updateDetails.bind(this);
        this.updateDescription = this.updateDescription.bind(this);
        this.updateStatus = this.updateStatus.bind(this);
        this.updatePrice = this.updatePrice.bind(this);
    }

    save() {
        if (!this.state.name || !this.state.status || !this.state.price) {
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
                name: this.state.name,
                details: this.state.details,
                description: this.state.description,
                status: this.state.status,
                price: this.state.price
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

    updateDetails(event) {
        this.setState({details: event.target.value});
    }

    updateDescription(event) {
        this.setState({description: event.target.value});
    }

    updateStatus(event) {
        this.setState({status: event.target.value});
    }

    updatePrice(event) {
        this.setState({price: event.target.value});
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
                                <div className="form-group">
                                    <label className="form-control-label">Details:</label>
                                    <input value={this.state.details} onChange={this.updateDetails} type="text"
                                           className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label className="form-control-label">Description:</label>
                                    <input value={this.state.description} onChange={this.updateDescription} type="text"
                                           className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label className="form-control-label">Status:</label>
                                    <input value={this.state.status} onChange={this.updateStatus} type="text"
                                           className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label className="form-control-label">Price:</label>
                                    <input value={this.state.price} onChange={this.updatePrice} type="text"
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