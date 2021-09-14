import React from 'react';
import {Link, useHistory} from 'react-router-dom';

const RentACar = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
            startDate: null,
            returnDate: null,
            clientId: "dadada"
        }
    );

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    };

    const onFormSubmit = (e) => {
        e.preventDefault();
        const startDate = formData.startDate;
        const returnDate = formData.returnDate;
        const clientId = formData.clientId;

        props.onRent(props.carType.id.id, props.car.id.id, startDate, returnDate, props.car.carPrice.currency, props.car.carPrice.amount, clientId);
        history.push("/rents");
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>

                    <div className="form-group">
                        <label>Client Id</label>
                        <select name="clientId" id="clientId" className="form-control" onChange={handleChange}>
                            {props.clients.map((client) =>
                                <option value={client.id.id}>{client.id.id}</option>
                            )}
                        </select>
                        <div className="form-group">
                            <label htmlFor="startDate">Start Date</label>
                            <input type="text"
                                   className="form-control"
                                   id="startDate"
                                   name="startDate"
                                   required
                                   placeholder="Enter Start Date"
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="returnDate">Return Date</label>
                            <input type="text"
                                   className="form-control"
                                   id="returnDate"
                                   name="returnDate"
                                   required
                                   placeholder="Enter Return Date"
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <h6>Car State</h6>
                            <h6>{props.car.carState.state}</h6>
                        </div>
                        <div className="form-group">
                            <h6>Amount</h6>
                            <h6>{props.car.carPrice.amount}</h6>
                        </div>
                        <div className="form-group">
                            <h6>Currency</h6>
                            <h6>{props.car.carPrice.currency}</h6>
                        </div>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Rent</button>
                </form>
            </div>
            <div className="col-md-5">
                <div className="card mb-4 shadow-sm">
                    <img
                        src={props.carType.imgUrl}
                        className="img-fluid"/>
                    <div className="card-body">
                        <p className="card-text">
                            <table className={"table table-striped"}>
                                <thead>
                                <tr>
                                    <th scope="col">Spec</th>
                                    <th scope="col">Value</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th scope={"col"}>Car Brand</th>
                                    <td>{props.carType.carBrand}</td>
                                </tr>
                                <tr>
                                    <th scope={"col"}>Car Name</th>
                                    <td>{props.carType.carName}</td>
                                </tr>
                                <tr>
                                    <th scope={"col"}>Year</th>
                                    <td>{props.carType.year}</td>
                                </tr>
                                <tr>

                                    <th scope={"col"}>Horse Power</th>
                                    <td>{props.carType.horsePower}</td>
                                </tr>
                                <tr>
                                    <th scope={"col"}>Engine Capacity</th>
                                    <td>{props.carType.engineCapacity}</td>
                                </tr>
                                <tr>

                                    <th scope={"col"}>Fuel Type</th>
                                    <td>{props.carType.fuelType}</td>
                                </tr>
                                <tr>
                                    <th scope={"col"}>Doors Number</th>
                                    <td>{props.carType.bodyType.doorsNumber}</td>
                                </tr>
                                <tr>
                                    <th scope={"col"}>Body Config</th>
                                    {props.carType.bodyType.hatchback ? <td>Hatchback</td> : <td>Caravan</td> }
                                </tr>
                                </tbody>
                            </table>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    )

};
export default RentACar;