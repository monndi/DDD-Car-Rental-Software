import React from 'react';
import {useHistory} from 'react-router-dom';

const AddClient = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        clientId: "",
        clientName: "",
        clientSurname: "",
        clientEmail: ""
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const clientId = formData.clientId;
        const clientName= formData.clientName;
        const clientSurname = formData.clientSurname;
        const clientEmail = formData.clientEmail;

        props.onAddClient(clientId, clientName, clientSurname, clientEmail);
        history.push("/clients");
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>

                    <div className="form-group">
                        <div className="form-group">
                            <label htmlFor="clientId">Client Id</label>
                            <input type="text"
                                   className="form-control"
                                   id="clientId"
                                   name="clientId"
                                   placeholder="Enter Client Id"
                                   required
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="clientName">Client Name</label>
                            <input type="text"
                                   className="form-control"
                                   id="clientName"
                                   name="clientName"
                                   placeholder="Client Name"
                                   required
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="clientSurname">Client Surname</label>
                            <input type="text"
                                   className="form-control"
                                   id="clientSurname"
                                   name="clientSurname"
                                   placeholder="clientSurname"
                                   required
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="clientEmail">Client Email</label>
                            <input type="text"
                                   className="form-control"
                                   id="clientEmail"
                                   name="clientEmail"
                                   placeholder="Client Email"
                                   required
                                   onChange={handleChange}
                            />
                        </div>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )

};
export default AddClient;