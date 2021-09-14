import React from 'react';
import {useHistory} from 'react-router-dom';

const CarTypeAdd = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        carBrand: "",
        carName: "",
        year: "",
        horsePower: 0,
        engineCapacity: 0,
        fuelType: "",
        doorsNumber: 0,
        hatchback: 0,
        imgUrl: "",
        currency: "USD",
        amount: 0

    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const carBrand = formData.carBrand;
        const carName= formData.carName;
        const year= formData.year;
        const horsePower= formData.horsePower;
        const engineCapacity= formData.engineCapacity;
        const fuelType= formData.fuelType;
        const doorsNumber= formData.doorsNumber;
        const hatchback= formData.hatchback;
        const imgUrl= formData.imgUrl;
        const currency= formData.currency
        const amount= formData.amount;

        props.onAddCarType(carBrand, carName, year, horsePower, engineCapacity, fuelType, doorsNumber, hatchback, imgUrl, currency, amount);
        history.push("/cars/catalog");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Car Brand</label>
                        <input type="text"
                               className="form-control"
                               id="carBrand"
                               name="carBrand"
                               required
                               placeholder="Enter Car Brand"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Car Name</label>
                        <input type="text"
                               className="form-control"
                               id="carName"
                               name="carName"
                               placeholder="Car Name"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Year</label>
                        <input type="text"
                               className="form-control"
                               id="year"
                               name="year"
                               placeholder="Year"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Horse Power</label>
                        <input type="text"
                               className="form-control"
                               id="horsePower"
                               name="horsePower"
                               placeholder="Horse Power"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Engine Capacity</label>
                        <input type="text"
                               className="form-control"
                               id="engineCapacity"
                               name="engineCapacity"
                               placeholder="Engine Capacity"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Fuel Type</label>
                        <input type="text"
                               className="form-control"
                               id="fuelType"
                               name="fuelType"
                               placeholder="Fuel Type"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Doors Number</label>
                        <input type="text"
                               className="form-control"
                               id="doorsNumber"
                               name="doorsNumber"
                               placeholder="Doors Number"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Hatch back</label>
                        <select name="hatchback" id="hatchback" className="form-control" onChange={handleChange}>
                            {["YES", "NO"].map((term) =>
                                <option value={term == "YES" ? true : false}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Img Url</label>
                        <input type="text"
                               className="form-control"
                               id="imgUrl"
                               name="imgUrl"
                               placeholder="Img Url"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Amount</label>
                        <input type="text"
                               className="form-control"
                               id="amount"
                               name="amount"
                               placeholder="Amount"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Currency</label>
                        <select name="currency" id="currency" className="form-control" onChange={handleChange}>
                            {["USD","EUR","MKD"].map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    {/*<div className="form-group">*/}
                    {/*    <label>Manufacturer</label>*/}
                    {/*    <select name="manufacturer" className="form-control" onChange={handleChange}>*/}
                    {/*        {props.manufacturers.map((term) =>*/}
                    {/*            <option value={term.id}>{term.name}</option>*/}
                    {/*        )}*/}
                    {/*    </select>*/}
                    {/*</div>*/}
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )

};
export default CarTypeAdd;