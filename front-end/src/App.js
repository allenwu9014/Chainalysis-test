import React, { useRef, useEffect, useState } from 'react';
import axios from 'axios';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

import CardItem from './CardItem';

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));

const App = () => {
    const [recommendationData, setRecommendationData] = useState();
    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
    axios.get('http://localhost:8080/recommend')
            .then((response) => {
                console.log(response);
                setRecommendationData(response.data);
            }).catch((error) => {
                console.log(error);
            }).then(()=>{
                console.log("Finally")
            });  
    },[]);
  
    useEffect(()=> {
        if (!recommendationData) return;
        setIsLoaded(true);
        console.log("do render")
      
    });
   
    

   
    return (

    <Grid container spacing={2} >
        <Grid item xs={12} bgcolor={"#3f51b5"}>
            <Typography sx={{ mt: 4, mb: 4}} variant="h3" component="div" textAlign="center" color = "white">
                Coins Recommendation
            </Typography>
        </Grid>
        <Grid item xs = {4} > </Grid>
        <Grid item xs = {2} >
            
            <Typography sx={{ mt: 4, mb: 2, paddingRight: 0}} variant="h4" component="div" textAlign= "center" >
                    BTC
            </Typography>
          
        </Grid>
        <Grid item xs = {2}>
            <Typography sx={{ mt: 4, mb: 2, paddingLeft: 10}} variant="h4" component="div" textAlign= "center" >
                     ETH
            </Typography>
        </Grid>
        <Grid item xs = {4} > </Grid>
        <Grid item xs={5} md ={6} container justifyContent="right"  >
                
            {recommendationData ? 
            recommendationData.exchangeBTCList.map((item, index) => {
           
            return ( 
                    <Item key = {index}>
                        <CardItem item = {item}/> 
                    </ Item>
                    );
                }

            ) : null}
        </Grid>
           
        <Grid item xs={5} md ={6} container justifyContent="left"  >
            
            {recommendationData ? 
            recommendationData.exchangeETHList.map((item, index) => {
           
            return (
                    <Item key = {index}>
                        <CardItem item = {item}/> 
                    </ Item>);
               }) : null }
        </Grid> 
       
        <Grid item xs={12} >

            <Typography sx={{ mt: 4, }} variant="h4" component="div" textAlign="center">
                Recommendation Buy and Sell
            </Typography>
        </Grid>
        
            <Grid item xs = {6}>
                
            <Typography sx={{ mt: 4, mb: 2, textAlign: "center"}} variant="h6" component="div">
                    BTC
            </Typography>
            </Grid>
            <Grid item xs = {6}>
            <Typography sx={{ mt: 4, mb: 2, textAlign: "center"}} variant="h6" component="div">
                     ETH
            </Typography>
            </Grid>
        
            <Grid item xs={1}   >
                </Grid>
              <Grid item xs={2}   >
              <Item elevation= {10}>
                <Typography variant="h5" component="div"> Where Buy BTC </Typography>
                <Typography variant="h5" component="div">
       
                     {recommendationData ? recommendationData.recommendBTCBuy.exchangeName : null}
                 </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 {recommendationData ? recommendationData.recommendBTCBuy.type : null}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Buy on  
                ${recommendationData ? recommendationData.recommendBTCBuy.ask : null}
                </Typography>

                <Button variant="contained" href={recommendationData ? recommendationData.recommendBTCBuy.link : null}>
                     GO to Buy
                </Button>
                </Item>
            </Grid>
            <Grid item xs={2}   >
            <Item elevation= {10}>
                <Typography variant="h5" component="div">Where Sell BTC </Typography>
                <Typography variant="h5" component="div">
       
                {recommendationData ? recommendationData.recommendBTCSell.exchangeName : null}
             </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 {recommendationData ? recommendationData.recommendBTCSell.type : null}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Sell on  
                ${recommendationData ?recommendationData.recommendBTCSell.bid: null}
                </Typography>
                <Button variant="contained" href={recommendationData ?recommendationData.recommendBTCSell.link: null}>
                    GO to Sell
                </Button>
                </Item>
            </Grid>
           
            <Grid item xs={2}   >
                </Grid>
            
            <Grid item xs={2}   >
            <Item elevation= {10}>
                <Typography variant="h5" component="div" color="text.secondary">Where Buy ETH </Typography>
                <Typography variant="h5" component="div">
       
                {recommendationData ?recommendationData.recommendETHBuy.exchangeName: null}
             </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 {recommendationData ?recommendationData.recommendETHBuy.type: null}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Buy on  
                ${recommendationData ?recommendationData.recommendETHBuy.ask: null}
                </Typography>
                <Button variant="contained" href={recommendationData ?recommendationData.recommendETHBuy.link: null}>
                    GO to Buy
                </Button>
                </Item>
            </Grid>
            <Grid item xs={2}   >
            <Item elevation= {10}>
                <Typography variant="h5" component="div">Where Sell ETH </Typography>
                <Typography variant="h5" component="div">
       
                {recommendationData ?recommendationData.recommendETHSell.exchangeName: null}
             </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 {recommendationData ?recommendationData.recommendETHSell.type: null}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Sell on  
                ${recommendationData ?recommendationData.recommendETHSell.bid: null}
                </Typography>
                <Button variant="contained" href={recommendationData ?recommendationData.recommendETHSell.link: null}>
                    GO to Sell
                </Button>
                </Item>
            </Grid>
          
            <Grid item xs={1}   >
                </Grid>
       <Grid item xs= {12} height = {100}/>
        
      </Grid>
      );
}

export default App;