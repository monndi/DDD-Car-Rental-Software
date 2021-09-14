import axios from "axios";

const instanceRents = axios.create({
    baseURL: 'http://localhost:9091/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
});

export default instanceRents;