import React from 'react';
import {useHistory} from 'react-router-dom';

const AddCar = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        carStatus: "FREE",
        carState: "GOOD",
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const carStatus = formData.carStatus;
        const carState= formData.carState;
        const id = props.carType.id.id;
        props.onAddCar(id, carStatus, carState);
        history.push(`/cars/catalog/${props.carType.id.id}`);
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>

                    <div className="form-group">
                        <label>Car State</label>
                        <select name="carState" id="carState" className="form-control" onChange={handleChange}>
                            {["GOOD", "PERFECT", "BAD"].map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                        <label>Car Status</label>
                        <select name="carStatus" id="carStatus" className="form-control" onChange={handleChange}>
                            {["FREE", "RENTED"].map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )

};
export default AddCar;