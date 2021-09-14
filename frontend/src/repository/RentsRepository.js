import axios from "../custom-axios/axiosRents";

const RentService = {
    fetchRents: () => {
        return axios.get("/rents");
    },
    deleteRent: (id) => {
        return axios.delete(`/rents/delete/${id}`);
    },
    rentACar: (carTypeId,carId, startDate, returnDate, currency, amount, clientId) => {
        return axios.post("/rents/rent", {
            "carTypeId": {
                "id": carTypeId
            },
            "carId": {
                "id": carId
            },
            "startDate": startDate,
            "returnDate": returnDate,
            "carPrice": {
                "currency": currency,
                "amount": amount
            },
            "clientId": {
                "id":clientId
            }
        });
    },
    changeReturnDate: (date, rentId) => {
        return axios.post("/rents/changeDate", {
            "rentId": {
                "id": rentId
            },
            "returnDate": date
        });
    },
    returnCar: (rentId, carState) => {
        return axios.post("/rents/returnCar", {
            "rentId": {
                "id": rentId
            },
            "carState": {
                "state": carState
            }
        })
    }
};

export default RentService;