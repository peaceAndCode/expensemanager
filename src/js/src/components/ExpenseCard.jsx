import React from 'react';
import {Button, Card, CardActions, CardContent, Grid, Typography} from "@mui/material";
const CardButton = ({buttonColor,icon}) =>{
    return(
        <Button variant="contained" color={buttonColor} sx={{ color:"#fff"}}>
            <i className={"fa-solid "+icon}></i>
        </Button>
    )
}
const ExpenseCard = () => {
    return (
        <Card sx={{ minWidth: 275 }}>
            <CardContent>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                    Category
                </Typography>
                <Typography variant="h5" component="div">
                    Title
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    descripton lorem ipsum dolor sit amet lorem ipsum dolor sit amet
                    lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor
                </Typography>
                <Typography variant="body2" sx={{fontWeight: "bold",marginBottom:"0.5rem"}}>
                    &euro; 20.00
                </Typography>
            </CardContent>
            <CardActions>
                <Grid container spacing={6} sx={{paddingLeft:"0.5rem",paddingRight:"0.5rem"}}>
                    <Grid item md={4}>
                        <CardButton buttonColor="success" icon="fa-pen-to-square"/>
                   </Grid>
                    <Grid item md={4}>
                        <CardButton buttonColor="error" icon="fa-trash"/>
                    </Grid>
                    <Grid item md={4}>
                        <CardButton buttonColor="primary" icon="fa-upload"/>
                    </Grid>
                </Grid>
            </CardActions>
        </Card>
    );
};

export default ExpenseCard;