"use client"

import React from 'react'
import {useState} from 'react'
import Link from 'next/link'

export default function Signup() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const updateUsername = (event:any) => {
        setUsername(event.target.value)
    }

    const updatePassword = (event:any) => {
        setPassword(event.target.value)
    }

    const handleSignup = async () => {
        const signupPostRequest = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                username: username,
                password: password
            })
        }

        try {
            const response = await fetch('http://localhost:8080/api/signup', signupPostRequest)

            if (response.ok) {
                alert("Signup successful!");
            } else {
                alert("Signup failed (fetch worked)");
            }
        } catch (error) {
            console.log(error)
        }
        
    }

    return (
    <main className='flex justify-center align-middle items-center m-[8rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w-md'>
            <h2 className='text-center text-black text-[1.7rem] mb-5'>Sign up</h2>
            <form className='m-auto'>
                <div id='username' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>User Name </label>
                    <input value={username}  onChange={updateUsername} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Enter Username' required>
                    </input>
                </div>

                <div id='password' className='grid'>
                    <label className='text-black'>Password </label>
                    <input value={password} onChange={updatePassword} type="password" className='bg-slate-100 text-black rounded p-2 ' placeholder='Enter Password' required>
                    </input>
                </div>

                <div id='signup-button' className='flex justify-center align-middle'>
                    <button onClick={handleSignup} className='bg-[#a0eafe] p-3 mt-6 rounded hover:bg-[#c5f2ff] w-full' >
                        <Link href='/' className='text-white'>Sign up</Link>
                    </button>
                </div>

            </form>
        </div>
    </main>
    )
}
