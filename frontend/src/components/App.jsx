// Dependencies
import React from 'react';

// Components
import Header from './header/Header.jsx';
import Content from './content/Content.jsx';
import Footer from './footer/Footer.jsx';
import ManageEquipment from './manageequipment/ManageEquipment.jsx';

// Styles
import './App.scss';

function App() {
    return (
        <div>
            <Header />
            <Content />
            <Footer />
        </div>
    );
}

export default App;