import {useState, useEffect} from 'react';
import Header from "../components/Header" 
import LeftBlockEmpty from "../components/LeftBlockEmpty"
import LoginForm from "../components/LoginForm"
import { API_BASE_URL } from '../config/config';

export default function LoginPage(){


    return (
        <>
            <Header textHeaderProp={"Bienvenido"}/>
            <div class="container">
                <LeftBlockEmpty />

                <LoginForm />
            </div>
        </>
    ) 
}
