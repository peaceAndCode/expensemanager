import React from 'react';
import styled from "styled-components";
import {Box, Button, Container, TextField} from "@mui/material";
import expenseManagerLogo from "../assets/expensemanager-logo.svg"
const Login = () => {
    return (
        <ContainerForm maxWidth="sm">
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
            <Button variant="contained">
                Sign in
            </Button>
        </ContainerForm>
    );
};

const ContainerForm = styled(Container)`
    margin-top: 8rem;
    margin-bottom: 8rem;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    & div img{
        margin-bottom: 2rem;
        height: 200px;
        width: 100%;
    }
    & form div .MuiTextField-root{
        width: 100%;
    }
    
    & form{
        margin-bottom: 2rem;
    }
    
    & button {
        width: 100%;
    }
`

export default Login;