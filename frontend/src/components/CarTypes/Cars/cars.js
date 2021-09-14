import React from "react";
import {Link} from "react-router-dom"

const cars = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Car State</th>
                            <th scope={"col"}>Car Status</th>
                            <th scope={"col"}>Currency</th>
                            <th scope={"col"}>Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.cars.map((car) => {
                            return (
                                <tr>
                                    <td>{car.carState.state}</td>
                                    <td>{car.carStatus.status}</td>
                                    <td>{car.carPrice.currency}</td>
                                    <td>{car.carPrice.amount}</td>
                                    <td>
                                    <Link className={"btn btn-info ml-2"}
                                          onClick={() => props.onRentCar(car.id.id, props.carType.id.id)}
                                          to={`/cars/rent/${props.carType.id.id}`}>
                                        Rent
                                    </Link>
                                    </td>
                                </tr>
                        );
                        })}
                        </tbody>
                    </table>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={`/cars/${props.carType.id.id}/add`}>Add new car</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );

};
export default cars;