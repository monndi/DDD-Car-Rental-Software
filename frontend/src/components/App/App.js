import React, {Component} from "react";
import './App.css';
import CarCatalogService from "../../repository/CarCatalogRepository";
import CarTypes from "../CarTypes/CarTypeList/carTypes"
import Cars from "../CarTypes/Cars/cars"
import Header from "../Header/header"
import Footer from "../Footer/footer"
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import Rents from "../Rents/RentList/rents"
import RentService from "../../repository/RentsRepository";
import CarTypeAdd from "../CarTypes/AddCarType/addCarType";
import AddCar from "../CarTypes/Cars/CarAdd/carAdd"
import RentCar from "../Rents/RentACar"
import AddClient from "../Clients/AddClient/addClient";
import ClientService from "../../repository/ClientsRepository";
import Clients from "../Clients/ClientList/clients"
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            carTypes: [],
            clients: [],
            rents: [],
            cars: [],
            selectedCarType: {},
            selectedCar: {}
        }
    }
    render() {
        return (
           <Router>
               <Header />
               <main>
                   <div className={"container"}>
                       <Route path={"/cars/catalog/:id"} exact render={() => <Cars cars={this.state.cars} carType={this.state.selectedCarType} onRentCar={this.loadCar}/>}/>
                       <Route path={"/cars/catalog"} exact render={() => <CarTypes carTypes={this.state.carTypes} onViewCars={this.loadCars}/>}></Route>
                       <Route path={"/cars/add"} exact render={() => <CarTypeAdd onAddCarType={this.addCarType}/>}></Route>
                       <Route path={"/rents"} exact render={() => <Rents onViewClientRents={this.viewClientRents} onChangeReturnDate={this.changeReturnDate} carTypes={this.state.carTypes} rents={this.state.rents} onReturnCar={this.returnCar} onDelete={this.deleteRent}/>}></Route>
                       <Route path={"/cars/:id/add"} exact render={() => <AddCar carType={this.state.selectedCarType} onAddCar={this.addCar}/>}/>
                       <Route path={"/cars/rent/:id"} exact render={() => <RentCar clients={this.state.clients} carType={this.state.selectedCarType} car={this.state.selectedCar} onRent={this.rentACar}/>}></Route>
                       <Route path={"/clients/add"} exact render={() => <AddClient onAddClient={this.addClient}/>}/>
                       <Route path={"/clients"} exact render={() => <Clients onDeleteClient={this.deleteClient} onViewClientRents={this.viewClientRents} clients={this.state.clients}/>}/>
                       <Redirect to={"/cars/catalog"}/>
                   </div>
               </main>
               <Footer/>
           </Router>
        );
    }
    componentDidMount() {
        this.loadCarTypes();
        this.loadRents();
        this.loadClients();
    }
    loadCarTypes = () => {
        CarCatalogService.fetchCars()
            .then((data) => {
                this.setState({
                    carTypes: data.data,
                    cars: data.data[0].availableCars,
                    selectedCarType: data.data[0]
                })
            });
    };
    loadRents = () => {
        RentService.fetchRents()
            .then((data)=>{
                this.setState({
                    rents: data.data
                })
            });
    };
    deleteRent  = (id) => {
        RentService.deleteRent(id)
            .then(() => {
                this.loadRents();
            })
    };
    deleteClient = (id) => {
        ClientService.deleteClient(id)
            .then(() => {
                this.loadClients();
            })
    };
    loadCar = (CarId, CarTypeId) => {
        this.setState({
            cars: this.state.carTypes.find(v => v.id.id === CarTypeId).availableCars,
            selectedCarType: this.state.carTypes.find(v => v.id.id === CarTypeId),
            selectedCar: this.state.carTypes.find(v => v.id.id === CarTypeId).carList.find(x=> x.id.id === CarId)
        })
    };

    loadCars = (id) => {
        // this.loadCarTypes();
        CarCatalogService.getCarById(id).then((data) => {
                this.setState({
                cars: data.data.availableCars,
                selectedCarType: data.data
            })
        });
    };
    addCarType = (carBrand, carName, year
                  , horsePowe, engineCapacity, fuelType, doorsNumber, hatchback, imgUrl, currency, amount) => {
        CarCatalogService.addCarType(carBrand, carName, year, horsePowe, engineCapacity, fuelType, doorsNumber, hatchback, imgUrl, currency, amount)
            .then(() => {
                this.loadCarTypes();
            });
    };

    addCar = (id, carStatus, carState) => {
        CarCatalogService.addCar(id, carStatus, carState)
            .then(() => {
                this.loadCars(id);
            });
    };

    rentACar = (carTypeId, carId, startDate, returnDate, currency, amount, clientId) => {
        RentService.rentACar(carTypeId,carId, startDate, returnDate, currency, amount, clientId)
            .then(()=>{
                this.loadRents();
            });
    };
    loadClients = () => {
        ClientService.fetchClients()
            .then((data)=>{
                this.setState({
                    clients: data.data
                })
            });
    };
    addClient = (clientId, clientName, clientSurname, clientEmail) => {
        ClientService.addClient(clientId, clientName, clientSurname, clientEmail)
            .then(() => {
                this.loadClients();
            });
    };
    changeReturnDate = (date, rentId) => {
        RentService.changeReturnDate(date, rentId)
            .then(() => {
                this.loadRents();
            });
    };

    returnCar = (rentId, carState) => {
        RentService.returnCar(rentId, carState)
            .then(() => {
                this.loadRents();
                this.loadCarTypes();
            });
    };
    viewClientRents = (clientId) => {
        ClientService.viewClientRents(clientId)
            .then((data) => {
                this.setState({
                    rents: data.data
                })
            });
    }

}

export default App;
