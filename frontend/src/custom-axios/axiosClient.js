import axios from "axios";

const instanceClients = axios.create({
    baseURL: 'http://localhost:9093/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
});

export default instanceClients;