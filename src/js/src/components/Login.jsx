import React from 'react';
import {Box, Button, TextField, Typography} from "@mui/material";
import expenseManagerLogo from "../assets/expensemanager-logo.svg"
import {Link} from "react-router-dom";
import {ContainerForm} from "../containers";

const Login = () => {
    return (
        <ContainerForm>
            <div>
                <img src={expenseManagerLogo} alt="expense manager logo"/>
            </div>
            <Box component="form">
                <div>
                    <TextField label="Username" variant="filled"/>
                </div>
                <div>
                    <TextField type="password" label="Password" variant="filled"/>
                </div>
            </Box>
            <div className="signup-link-container">
                <Typography variant="subtitle1" component="p">
                    You don't have an account yet? <Link to="/signup">Sign up</Link>
                </Typography>
            </div>
            <Button variant="contained">
                Sign in
            </Button>
        </ContainerForm>
    );
};
export default Login;