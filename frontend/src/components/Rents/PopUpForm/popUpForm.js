import React from "react";
import "./popUpForm.css"
function PopUp(props) {
    return (props.trigger) ?
    (
        <div className="popup">
            <div className="popup-inner">
                {props.children}
            </div>
        </div>) : "";
}
export default PopUp;