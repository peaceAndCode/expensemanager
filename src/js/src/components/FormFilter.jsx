import React from 'react';
import {Grid, MenuItem, TextField} from "@mui/material";
import styled from "styled-components";

const FormFilter = () => {
    return (
        <GridCustom container spacing={2}>
            <Grid item md={4} sm={12}>
                <TextField
                    select
                    label="Date filter"
                    defaultValue=""
                >
                    <MenuItem value=""></MenuItem>
                    <MenuItem value="ASC">Ascending</MenuItem>
                    <MenuItem value="DESC">Descending</MenuItem>
                </TextField>
            </Grid>
            <Grid item md={4} sm={12}>
                <TextField
                    select
                    label="Category filter"
                    defaultValue=""
                >
                    <MenuItem value=""></MenuItem>
                </TextField>
            </Grid>
            <Grid item md={4} sm={12}>
                <TextField
                    select
                    label="Price filter"
                    defaultValue=""
                >
                    <MenuItem value=""></MenuItem>
                    <MenuItem value="ASC">Ascending</MenuItem>
                    <MenuItem value="DESC">Descending</MenuItem>
                </TextField>
            </Grid>
        </GridCustom>
    );
};

const GridCustom = styled(Grid)`
    padding-top: 1.5rem;
    padding-bottom: 2rem;
    & div{
        margin-bottom: 0.3rem;
        width: 100%;
    }
`

export default FormFilter;