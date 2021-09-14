import axios from "../custom-axios/axiosClient";

const ClientService = {

    fetchClients: () => {
        return axios.get("/client");
    },
    addClient: (clientId, clientName, clientSurname, clientEmail) => {
        return axios.post("/client/add", {
            "clientId": clientId,
            "clientName": clientName,
            "clientSurname": clientSurname,
            "clientEmail": clientEmail
        });
    },
    viewClientRents: (clientId) => {
        return axios.get(`/client/rents/${clientId}`);
    }
};

export default ClientService;