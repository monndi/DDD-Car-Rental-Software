import React from "react";
import {Link} from "react-router-dom"

const clients = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Client ID</th>
                            <th scope={"col"}>Client Name</th>
                            <th scope={"col"}>Client Surname</th>
                            <th scope={"col"}>Client Email</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.clients.map((client) => {
                            return (
                                <tr>
                                    <td>{client.id.id}</td>
                                    <td>{client.clientName}</td>
                                    <td>{client.clientSurname}</td>
                                    <td>{client.clientEmail}</td>

                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={`/clients/add`}>Add new client</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );

};
export default clients;