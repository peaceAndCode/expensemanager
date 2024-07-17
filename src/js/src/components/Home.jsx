import React from 'react';
import {ContainerMain} from "../containers";
import FormFilter from "./FormFilter";
import ExpenseContainer from "./ExpenseContainer";

const Home = () => {
    return (
        <ContainerMain>
            <FormFilter/>
            <ExpenseContainer/>
        </ContainerMain>
    );
};

export default Home;