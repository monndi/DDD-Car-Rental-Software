import axios from "../custom-axios/axiosCatalog";


const CarCatalogService = {
    fetchCars: () => {
        return axios.get("/cartype");
    },
    addCarType: (carBrand, carName, year, horsePowe, engineCapacity, fuelType, doorsNumber, hatchback, imgUrl, currency, amount) => {
        return axios.post("/cartype/add", {
            "carBrand": carBrand,
            "carName": carName,
            "year": year,
            "horsePower": horsePowe,
            "engineCapacity": engineCapacity,
            "fuelType": fuelType,
            "bodyType": {
            "doorsNumber": doorsNumber,
            "hatchback": hatchback
            },
            "imgUrl": imgUrl,
            "currency":currency,
            "amount":amount
        });
    },
    addCar: (id, carStatus, carState) => {
        return axios.put(`/cartype/addCar/${id}`, {
            "carStatus": carStatus,
            "carState": carState,
        });
    },
    getCarById: (id) => {
        return axios.get(`/cartype/getCar/${id}`);
    }
};

export default CarCatalogService;