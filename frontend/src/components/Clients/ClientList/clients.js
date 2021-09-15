import React, {Component} from "react";
import {Link} from "react-router-dom"
import ClientService from "../../../repository/ClientsRepository";

function Clients(props) {
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
                            {props.clients.map((clientRents) => {
                                return (
                                    <tr>
                                        <td>{clientRents.client.id.id}</td>
                                        <td>{clientRents.client.clientName}</td>
                                        <td>{clientRents.client.clientSurname}</td>
                                        <td>{clientRents.client.clientEmail}</td>
                                        <td className={"text-right"}>
                                            <Link className={"btn btn-info ml-2"}
                                                  onClick={() => props.onViewClientRents(clientRents.client.id.id)} to={"/rents"}>
                                                View Client Rents</Link>
                                            {clientRents.rents.length > 0 ? (" ") :
                                                (<a title={"Delete Client"} className={"btn btn-danger"}
                                                                                                      onClick={() => props.onDeleteClient(clientRents.client.id.id)}>Delete Client
                                            </a>)}
                                        </td>
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
export default Clients;