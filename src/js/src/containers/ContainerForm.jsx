import {Container} from "@mui/material";

import React from 'react';
import styled from "styled-components";
import {Footer} from "../components";

const ContainerForm = ({children}) => {
    return (
        <ContainerCustom maxWidth="md">
            {children}
            <Footer/>
        </ContainerCustom>
    );
};

const ContainerCustom = styled(Container)`
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
        margin-bottom: 1rem;
    }
    
    & div.signup-link-container{
        margin-bottom: 1rem;
    }

    & div.signin-link-container{
        margin-bottom: 1rem;
    }
    
    & button {
        width: 100%;
    }
`

export default ContainerForm;