import { AppBar, Button, IconButton, makeStyles, Toolbar, Typography} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu'
import Box from '@mui/material/Box';
import React from 'react';

const Header = () => {
    return ( 
    <Box sx = {{ flexGrow:1 }}>
        <AppBar postion="static">
            <Toolbar>
                <IconButton edge="start" color="inherit" aria-label='menu' sx = {{ mr:2 }}>
                    <MenuIcon />
                </IconButton>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>Dive Log</Typography>
                <Button color="inherit">ClickMe</Button>
            </Toolbar>
        </AppBar>
    </Box> 
    );
};

export default Header;