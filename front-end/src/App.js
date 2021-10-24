import React, { useRef, useEffect, useState } from 'react';
import axios from 'axios';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import CircularProgress from '@mui/material/CircularProgress';
import Switch from '@mui/material/Switch';
import LoadingButton from '@mui/lab/LoadingButton';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';

import CardItem from './CardItem';

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));
let interval;
const App = () => {
    const [recommendationData, setRecommendationData] = useState();
    const [isDataLoaded, setIsDataLoaded] = useState(false);
    const [checked, setChecked] = useState(true);
    const [loading, setLoading] = useState(false);
    

    useEffect(() => {
       
    axios.get('http://localhost:8080/recommend')
            .then((response) => {
                console.log(response);
                setRecommendationData(response.data);
                setLoading(false);
                setIsDataLoaded(true);
            }).catch((error) => {
                console.log(error);
            }).then(()=>{
                console.log("Finally")
            });  
            
    },[loading]);
    
    useEffect(() => {
        console.log(checked);
        if (checked == true) {
            interval = setInterval(autoRefreshDataHandler, 5000);
        } 
        else{
            clearInterval(interval);
            interval = 0;
            console.log("clearRefreashData")
       } 
        

        
    },[checked]);

    const autoRefreshDataHandler = () => {
        axios.get('http://localhost:8080/recommend')
            .then((response) => {
                console.log(response);
                setRecommendationData(response.data);
                setLoading(false);
                setIsDataLoaded(true);
            }).catch((error) => {
                console.log(error);
            }).then(()=>{
                console.log("Finally")
            });  
    }

    const handleChange = (event) => {
        setChecked(event.target.checked);
        console.log(checked);
        console.log(event.target.checked);
      
      };
    
    const refreshButtonHandler= () => {
        setLoading(true);
        setIsDataLoaded(false);
    
      }
   
    return (

    <Grid container spacing={2} >
        <Grid item xs={9} bgcolor={"#3f51b5"}>
            <Typography sx={{ mt: 4, mb: 4}} variant="h3" component="div" textAlign="center" color = "white">
                Coins Recommendation
            </Typography>
            
        </Grid>
        <Grid item xs={3} bgcolor={"#3f51b5"}>
        
        <Typography sx={{ mt: 7, mb: 4, textAlign:"right", paddingRight:10}} variant="h6" component="div" textAlign="center" color = "white">
        
            {  checked ? "   Auto Refresh On" : "   Auto Refresh Off" }
                <Switch
      checked={checked}
      onChange={handleChange}
      inputProps={{ 'aria-label': 'controlled' }}
    />
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
        <Grid item xs = {4} textAlign="right" paddingRight={15}>
           {!checked ?
        <LoadingButton
        onClick={refreshButtonHandler}
        loading={loading}
        endIcon={<ChevronRightIcon />}
        color="secondary"
        variant="contained"
        size="large" 
        loadingPosition="end"
       
      > Refresh</LoadingButton> : null
           }
     
             </Grid>
        <Grid item xs={5} md ={6} container justifyContent="right"  >
                
            {recommendationData && isDataLoaded ? 
           recommendationData.exchangeBTCList.map((item, index) => {
                         return ( 
                            <Item key = {index}>
                                <CardItem item = {item}/> 
                            </ Item>
                            );
                        }
        
                    ) : <Item elevation= {0}>
                    <Typography sx={{ mt: 4, }} variant="h5" component="div" textAlign="center">
                    BTC Data Loading...
                    </Typography>
                   <CircularProgress />
                   </Item>}
        </Grid>
           
        <Grid item xs={5} md ={6} container justifyContent="left"  >
            
            {recommendationData && isDataLoaded ? 
            recommendationData.exchangeETHList.map((item, index) => {
            return (
                    <Item key = {index}>
                        <CardItem item = {item}/> 
                    </ Item>);
               }) : <Item elevation= {0}>
                    <Typography sx={{ mt: 4, }} variant="h5" component="div" textAlign="center">
                    ETH Data Loading...
                    </Typography>
                   <CircularProgress />
                   </Item>
                    }
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
                  {recommendationData  && isDataLoaded?
              <Item elevation= {10}>
                <Typography variant="h5" component="div"> Where to Buy BTC </Typography>
                <Typography variant="h5" component="div">
       
                { recommendationData.recommendBTCBuy.exchangeName }
                 </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 {recommendationData.recommendBTCBuy.type }
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Buy on  
                ${recommendationData.recommendBTCBuy.ask }
                </Typography>

                <Button variant="contained" href={ recommendationData.recommendBTCBuy.link }>
                     GO to Buy
                </Button>
                </Item> : <Item elevation= {0}>
                    <Typography sx={{ mt: 4, }} variant="h5" component="div" textAlign="center">
                    BTC Data Loading...
                    </Typography>
                   <CircularProgress />
                   </Item>}
            </Grid>
            <Grid item xs={2}   >
            {recommendationData  && isDataLoaded?
            <Item elevation= {10}>
                <Typography variant="h5" component="div">Where to Sell BTC </Typography>
                <Typography variant="h5" component="div">
       
                {recommendationData.recommendBTCSell.exchangeName}
             </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 { recommendationData.recommendBTCSell.type }
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Sell on  
                ${recommendationData.recommendBTCSell.bid}
                </Typography>
                <Button variant="contained" href={recommendationData.recommendBTCSell.link}>
                    GO to Sell
                </Button>
                </Item> : <Item elevation= {0}>
                    <Typography sx={{ mt: 4, }} variant="h5" component="div" textAlign="center">
                    BTC Data Loading...
                    </Typography>
                   <CircularProgress />
                   </Item>}
            </Grid>
           
            <Grid item xs={2}   >
                </Grid>
            
            <Grid item xs={2}   >
            {recommendationData  && isDataLoaded?
            <Item elevation= {10}>
                <Typography variant="h5" component="div" color="text.secondary">Where to Buy ETH </Typography>
                <Typography variant="h5" component="div">
       
                {recommendationData.recommendETHBuy.exchangeName}
             </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 {recommendationData.recommendETHBuy.type}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Buy on  
                ${recommendationData.recommendETHBuy.ask}
                </Typography>
                <Button variant="contained" href={recommendationData.recommendETHBuy.link}>
                    GO to Buy
                </Button>
                </Item> : <Item elevation= {0}>
                    <Typography sx={{ mt: 4, }} variant="h5" component="div" textAlign="center">
                    ETH Data Loading...
                    </Typography>
                   <CircularProgress />
                   </Item>}
            </Grid>
            <Grid item xs={2}   >
            {recommendationData  && isDataLoaded?
            <Item elevation= {10}>
                <Typography variant="h5" component="div">Where to Sell ETH </Typography>
                <Typography variant="h5" component="div">
       
                {recommendationData.recommendETHSell.exchangeName}
             </Typography>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
                 Coin:
                 {recommendationData.recommendETHSell.type}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                 Sell on  
                ${recommendationData.recommendETHSell.bid}
                </Typography>
                <Button variant="contained" href={recommendationData.recommendETHSell.link}>
                    GO to Sell
                </Button>
                </Item> : <Item elevation= {0}>
                    <Typography sx={{ mt: 4, }} variant="h5" component="div" textAlign="center">
                    ETH Data Loading...
                    </Typography>
                   <CircularProgress />
                   </Item>}
            </Grid>
          
            <Grid item xs={1}   >
                </Grid>
                <Grid item xs= {12} height = {50} />
       <Grid item xs= {12} height = {50} bgcolor={"#3f51b5"}>
           </Grid>
        
      </Grid>
      );
}

export default App;