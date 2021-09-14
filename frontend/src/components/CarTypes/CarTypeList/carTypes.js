import React from "react";
import './carTypes.css';
import {Link} from 'react-router-dom';
import CarTypeDetails from "../CarTypeDetails/carTypeDetails"
const carTypesAlbum = (props) => {
    return (
        <div>
                <section className="jumbotron text-center">
                    <div className="container">
                        <h1>Cars Catalog</h1>
                        <p className="lead text-muted">Cars you can rent, with specification, state, availability and price! </p>
                        <p>
                            <Link className="btn btn-primary my-2 mr-2" to={"/cars/add"}>Add New Car Type</Link>
                            <Link className="btn btn-secondary my-2" to={"/clients/add"}>Register New Client</Link>
                        </p>
                    </div>
                </section>
                <div className="album py-5 bg-light">
                    <div className="container">

                        <div className="row">
                            {props.carTypes.map((carType) => {
                                return (
                                    <CarTypeDetails carType={carType} onViewCars={props.onViewCars}/>
                                    );
                            })}
                        </div>
                    </div>
                </div>
        </div>
        )
};
export default carTypesAlbum;