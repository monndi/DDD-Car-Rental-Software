import React from 'react';
import {Link} from 'react-router-dom';

const footer = (props) => {
    return (
        <footer className="text-muted">
        <div className="container" align={"center"} >
            <p className="float-right">
                <a className="btn btn-outline-info my-2 my-sm-0" href="#">Back to top</a>
            </p>
            <p >Copyright &copy; 2021 CAR RENTAL SOFTWARE. All Rights Reserved</p>
            {/*<p>New to Bootstrap? <a href="https://getbootstrap.com/">Visit the homepage</a> or read our <a*/}
            {/*    href="/docs/4.5/getting-started/introduction/">getting started guide</a>.</p>*/}
        </div>
    </footer>)
};
export default footer;
