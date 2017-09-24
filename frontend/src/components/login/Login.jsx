import React from 'react';
import 'whatwg-fetch'

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.updateLogin = this.updateLogin.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
        this.login = this.login.bind(this);
    }

    updateLogin(event) {
        this.setState({login: event.target.value});
    }

    updatePassword(event) {
        this.setState({password: event.target.value});
    }

    login() {
        this.setState({error: false});
        fetch('http://localhost:8080/oauth/token?client_id=browser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: this.state.login,
                password: this.state.password
            })
        }).then(response => {
            if (response.status === 200) {
                response.json()
                    .then((it) => {
                        localStorage.setItem('token', it.access_token);
                        this.props.history.push('/equipment');
                    });
            } else {
                this.setState({error: true});
            }

        })
    }

    render() {
        return (
            <div className="container">

                <form className="form-signin">
                    <h2 className="form-signin-heading">Please sign in</h2>
                    <label htmlFor="inputEmail" className="sr-only">Login</label>
                    <input value={this.state.login} onChange={this.updateLogin}
                           type="text" id="inputEmail"
                           className="form-control"
                           placeholder="Login"
                           required=""
                           autoFocus=""/>
                    <label htmlFor="inputPassword" className="sr-only">Password</label>
                    <input type="password"
                           value={this.state.password} onChange={this.updatePassword}
                           id="inputPassword"
                           className="form-control"
                           placeholder="Password"
                           required=""/>
                    <button onClick={this.login} className="btn btn-lg btn-primary btn-block" type="submit">Sign in
                    </button>
                </form>
                {this.state.error ? <div className="alert alert-danger" role="alert">
                    <strong>Error!</strong> Login or password incorrect
                </div> : ''}

            </div>
        );
    }
}

export default Login;