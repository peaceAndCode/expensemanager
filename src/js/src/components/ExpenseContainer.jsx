import React from 'react';
import {Grid} from "@mui/material";
import ExpenseCard from "./ExpenseCard";

const ExpenseContainer = () => {
    return (
        <Grid container spacing={6}>
            {
                Array(50).fill().map((_,i) => (
                    <Grid item md={4} sm={12}>
                        <ExpenseCard/>
                    </Grid>
                ))
            }
        </Grid>
    );
};

export default ExpenseContainer;