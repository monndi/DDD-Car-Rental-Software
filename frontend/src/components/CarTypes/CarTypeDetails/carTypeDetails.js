import React from "react";
import {Link} from "react-router-dom";

const carTypeDetails = (props) => {
    return (<React.Fragment>

        <div className="col-md-4">
            <div className="card mb-4 shadow-sm">
                <img
                    src={props.carType.imgUrl}
                    className="CarListItem-img"/>
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
                            {/*<th scope={"col"}>Cars</th>*/}
                            {/*<Cars cars={carType.carList}/>*/}
                            </tbody>
                        </table>
                    </p>
                    <div className="d-flex justify-content-between align-items-center">
                        <div className="btn-group">
                            <Link className={"btn btn-sm btn-outline-secondary"} onClick={() => props.onViewCars(props.carType.id.id)}
                                  to={`/cars/catalog/${props.carType.id.id}`}>
                                View Cars
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </React.Fragment>);
}
export default carTypeDetails;