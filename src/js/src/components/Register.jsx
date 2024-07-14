import React from 'react';
import {Button, TextField, MenuItem, Typography, Grid} from "@mui/material";
import expenseManagerLogo from "../assets/expensemanager-logo.svg"
import {Link} from "react-router-dom";
import {ContainerForm} from "../containers";
const Register = () => {
    return (
        <ContainerForm>
            <div>
                <img src={expenseManagerLogo} alt="expense manager logo"/>
            </div>
            <Grid container spacing={2} component="form">
                <Grid item xs={12} md={6}>
                   <TextField label="First name" variant="filled"/>
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField type="password" label="Last name" variant="filled"/>
                </Grid>
                <Grid item md={12}>
                    <TextField label="E-mail" variant="filled"/>
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField label="Password" variant="filled"/>
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField select label="Currency" defaultValue="EUR"  variant="filled">
                        <MenuItem key="EUR" value="EUR">€</MenuItem>
                    </TextField>
                </Grid>
            </Grid>

            <div className="signin-link-container">
                <Typography variant="subtitle1" component="p">
                    Do you already have an account? <Link to="/signin">Sign in</Link>
                </Typography>
            </div>

            <Button variant="contained">
                Sign up
            </Button>
        </ContainerForm>
    );
};
export default Register;