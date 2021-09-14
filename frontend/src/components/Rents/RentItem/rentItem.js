import React, {Component} from "react";
import {Link} from "react-router-dom";
import PopUp from "../PopUpForm/popUpForm"
const rentItem = (props) => {
    const carType = props.carTypes.find(carType => carType.id.id === props.rent.carTypeId.id);
    const car = carType.carList.find(car => car.id.id === props.rent.carId.id);
    const getParsedDate = (strDate) => {
        var strSplitDate = String(strDate).split(' ');
        var date = new Date(strSplitDate[0]);
        // alert(date);
        var dd = date.getDate();
        var mm = date.getMonth() + 1; //January is 0!

        var yyyy = date.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        date = dd + "/" + mm + "/" + yyyy;
        return date.toString();
    }

    return (
        <tr>
            <td scope={"col"}>{props.rent.clientId.id}</td>
            <td scope={"col"}>{getParsedDate(props.rent.rentDuration.startDate)}</td>
            <td scope={"col"}>{getParsedDate(props.rent.rentDuration.returnDate)}</td>
            <td scope={"col"}>{props.rent.totalPrice.amount}</td>
            <td scope={"col"}>{props.rent.totalPrice.currency}</td>
            <td scope={"col"}>{carType.carBrand}</td>
            <td scope={"col"}>{carType.carName}</td>
            <td scope={"col"}>{carType.year}</td>
            <td scope={"col"}>{car.carState.state}</td>
            <td className={"text-right"}>
                {/*<a title={"Delete"} className={"btn btn-danger"}*/}
                {/*   onClick={() => props.onDelete(props.rent.id.id)}>*/}
                {/*    Delete*/}
                {/*</a>*/}
                <a className={"btn btn-outline-dark my-2 my-sm-0"}
                   onClick={() => props.onDateChange(props.rent)}>
                    Change Date</a>
                <Link className={"btn btn-info ml-2"}
                    onClick={() => props.onViewClientRents(props.rent.clientId.id)}
                      to={"/rents"}>
                    View Client Rents</Link>
                <a title={"Return Car"} className={"btn btn-danger"}
                   onClick={() => props.onReturn(props.rent, car.carState)}>Return
                </a>
            </td>

        </tr>
    );
};
export default rentItem;