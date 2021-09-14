import React, {Component} from "react";
import { useState } from "react";
import RentItem from "../RentItem/rentItem"
import PopUp from "../PopUpForm/popUpForm";
import {useHistory} from 'react-router-dom';

function Rents(props) {
    const [buttonPopup, setButtonPopup] = useState(false);
    const [changeDateButton, setChangeDateButton] = useState(false);

    const [rent, setRent] = useState({
            "id": {
                "id": ""
            },
            "totalPrice": {
            "amount": 0,
            "currency": "USD"
        }});
    const [carState, setCarState] = useState({"carState": {
            "state": ""
        }});
    const [formData, updateFormData] = useState({
        carState: "GOOD",
        returnDate: ""
    });
    const popUp = (rentItem) => {
        this.setState({
            rent: rentItem,
            buttonPopup: true
        })
    };
    const history = useHistory();

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    };

    const onFormSubmit = (e) => {
        e.preventDefault();
        const rentId = rent.id.id;
        const carState = formData.carState;
        props.onReturnCar(rentId, carState);
        setButtonPopup(false);
        history.push("/rents");
    };

    const onFormSubmit1 = (e) => {
        e.preventDefault();
        const returnDate = formData.returnDate;
        const rentId = rent.id.id;
        props.onChangeReturnDate(returnDate, rentId);
        setChangeDateButton(false);
        history.push("/rents");
    };

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Client ID</th>
                                <th scope={"col"}>Start Date</th>
                                <th scope={"col"}>Return Date</th>
                                <th scope={"col"}>Car Price</th>
                                <th scope={"col"}>Currency</th>
                                <th scope={"col"}>Car Type</th>
                                <th scope={"col"}>Car Name</th>
                                <th scope={"col"}>Car Year</th>
                                <th scope={"col"}>Car State</th>
                            </tr>
                            </thead>
                            <tbody>
                            {props.rents.map((rent) => {
                                return (
                                    <RentItem rent={rent} carTypes={props.carTypes} onDateChange={(rent) => { setChangeDateButton(true); setRent(rent)}} onReturn={(rent, state) =>{ setButtonPopup(true); setRent(rent); setCarState(state)}}
                                              onViewClientRents={props.onViewClientRents} onDelete={props.onDelete}/>
                                );
                            })}
                            </tbody>
                        </table>
                    </div>
                </div>

                <PopUp trigger={buttonPopup}>
                    <div>
                        <form onSubmit={onFormSubmit}>
                            <div className="form-group">
                                <h5>Price</h5>
                                <h6>{rent.totalPrice.amount}</h6>
                                <h5>Currency</h5>
                                <h6>{rent.totalPrice.currency}</h6>
                                <label>Car State</label>
                                <select name="carState" id="carState" className="form-control" onChange={handleChange}>
                                    {["GOOD", "PERFECT", "BAD"].map((term) =>
                                        <option value={term}>{term}</option>
                                    )}
                                </select>
                            </div>
                            <button id="submit" type="submit" className="btn btn-primary">Return Car</button>
                            <button className="btn btn-outline-danger" onClick={() => setButtonPopup(false)}>Cancel</button>
                        </form>
                    </div>
                </PopUp>
                <PopUp trigger={changeDateButton}>
                    <div>
                        <form onSubmit={onFormSubmit1}>
                            <div className="form-group">
                                <div className="form-group">
                                    <label htmlFor="startDate">Return Date</label>
                                    <input type="text"
                                           className="form-control"
                                           id="returnDate"
                                           name="returnDate"
                                           required
                                           placeholder="Enter New Return Date"
                                           onChange={handleChange}
                                    />
                                </div>
                            </div>
                            <button id="submit" type="submit" className="btn btn-primary">Change Return Date</button>
                            <button className="btn btn-outline-danger" onClick={() => setChangeDateButton(false)}>Cancel</button>
                        </form>
                    </div>
                </PopUp>
            </div>

        );

};

export default Rents;