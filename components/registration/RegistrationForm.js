import React, {useState,setState} from 'react';
// import './style.css'
function RegistrationForm() {
    
    const [firstName, setFirstName] = useState(null);
    const [lastName, setLastName] = useState(null);
    const [email, setEmail] = useState(null);
    const [password,setPassword] = useState(null);
    const [confirmPassword,setConfirmPassword] = useState(null);

    const handleInputChange = (e) => {
        const {id , value} = e.target;
        if(id === "firstName"){
            setFirstName(value);
        }
        if(id === "lastName"){
            setLastName(value);
        }
        if(id === "email"){
            setEmail(value);
        }
        if(id === "password"){
            setPassword(value);
        }
        if(id === "confirmPassword"){
            setConfirmPassword(value);
        }

    }

    const handleSubmit  = () => {
        console.log(firstName,lastName,email,password,confirmPassword);
    }

    return(
        <div className="form p-10 m-auto text-gray-600 border-r-8">
            <div className="form-body text-left  border-gray-500 p-5 m-auto space-between">
                <div className="firstname p-5 justify-between ">
                    {/* <arealabel className="form__label w-40%" for="firstName">First Name </arealabel> */}
                    <input className="form__input p-5 w-60% border border-gray-500 bg-black text-white outline-none focus:border-[#1d9bf0]" type="text" value={firstName} onChange = {(e) => handleInputChange(e)} id="firstName" placeholder="First Name"/>
                </div>
                <div className="lastname p-5 ">
                    {/* <label className="form__label" for="lastName">Last Name </label> */}
                    <input  type="text" name="" id="lastName" value={lastName}  className="form__input p-5 w-60% border border-gray-500 bg-black text-white outline-none focus:border-[#1d9bf0]" onChange = {(e) => handleInputChange(e)} placeholder="LastName"/>
                </div>
                <div className="email p-5">
                    {/* <label className="form__label" for="email">Email </label> */}
                    <input  type="email" id="email" className="form__input p-5 w-60% border border-gray-500 bg-black text-white outline-none focus:border-[#1d9bf0]" value={email} onChange = {(e) => handleInputChange(e)} placeholder="Email"/>
                </div>
                <div className="password p-5">
                    {/* <label className="form__label" for="password">Password </label> */}
                    <input className="form__input p-5 w-60% border border-gray-500 bg-black text-white outline-none focus:border-[#1d9bf0]" type="password"  id="password" value={password} onChange = {(e) => handleInputChange(e)} placeholder="Password"/>
                </div>
                <div className="confirm-password p-5">
                    {/* <label className="form__label" for="confirmPassword">Confirm Password </label> */}
                    <input className="form__input p-5 w-60% border border-gray-500 bg-black text-white outline-none focus:border-[#1d9bf0]" type="password" id="confirmPassword" value={confirmPassword} onChange = {(e) => handleInputChange(e)} placeholder="Confirm Password"/>
                </div>
            </div>
            <div class="footer text-center bg-blue-500 w-60% text-white ">
                <button onClick={()=>handleSubmit()} type="submit" class="btn">Register</button>
            </div>
        </div>
       
    )       
}

export default RegistrationForm