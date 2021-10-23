

const RecommendCard = (props) => {
    return (
        <Item> 
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
        </Item>
    );
}
export default RecommendCard;