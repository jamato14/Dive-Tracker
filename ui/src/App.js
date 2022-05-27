import './App.css';
import { Button, Grid, makeStyles } from '@mui/material'
import Header from './components/Header';

const App = () => {
  return (
    
      <Grid container direction="column" justifyContent="center" alignItems="center">
        <Grid item>
          <Header />
        </Grid>
        <Grid item>
          Other rows
        </Grid>
      </Grid>
    
  );
};

export default App;
