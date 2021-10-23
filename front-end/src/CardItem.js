import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';

const bull = (
  <Box
    component="span"
    sx={{ display: 'inline-block', mx: '2px', transform: 'scale(0.8)' }}
  >
    •
  </Box>
);

const card = (item) => {
  return (
  <React.Fragment>
    <CardContent>
    <Typography variant="h5" component="div">
       
        {item.exchangeName}
      </Typography>
      <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
      
        Coin:
      {item.type}
      </Typography>
      <Typography sx={{ mb: 1.5 }} color="text.secondary">
        ASK: 
      ${item.ask}
      </Typography>
      <Typography sx={{ mb: 1.5 }} color="text.secondary">
       BID: 
        ${item.bid}
      
      </Typography>
    </CardContent>
    <CardActions>
      <Button size="small">
        <Link href={item.link} underline="none">Go for Order </Link>
      </Button>
    </CardActions>
  </React.Fragment>
  );
}

const CardItem = (props) => {

    return (
    <Box sx={{ minWidth: 275, maxWidth: 275 }}>
      <Card variant="outlined">{card(props.item)}</Card>
    </Box>
    );
}

export default CardItem;