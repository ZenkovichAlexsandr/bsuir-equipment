// Dependencies
import React from 'react';

// Components
import Header from './header/Header.jsx';
import Content from './content/Content.jsx';
import Footer from './footer/Footer.jsx';
import Login from './login/Login.jsx';

// Styles
import './App.scss';
import {Route, Switch} from 'react-router-dom';

function App() {
    return (
        <div>
            <Header/>
            <Switch>
                <Route path='/equipment' component={Content}/>
                <Route exact path='/' component={Login}/>
            </Switch>
            <Footer/>
        </div>
    );
}

export default App;