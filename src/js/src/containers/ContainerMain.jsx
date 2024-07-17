import {Container} from "@mui/material";

import React from 'react';
import styled from "styled-components";
import {Footer} from "../components";
import Menu from "../components/Menu";

const ContainerMain = ({children}) => {
    return (
        <>
            <Menu/>
            <ContainerCustom >
                {children}
            </ContainerCustom>
            <Footer/>
        </>

    );
};

const ContainerCustom = styled(Container)`
    margin-bottom: 8rem;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    min-height: 300px;
`

export default ContainerMain;