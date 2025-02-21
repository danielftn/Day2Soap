"use client"

import React from 'react'
import Link from 'next/link'

export default function Signup() {
  return (
    <main className='flex justify-center align-middle items-center m-[8rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w-md'>
            <h2 className='text-center text-black text-[1.7rem] mb-5'>Sign up</h2>
            <form className='m-auto'>
                <div id='username' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>User Name </label>
                    <input type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Enter Username' required>
                    </input>
                </div>

                <div id='password' className='grid'>
                    <label className='text-black'>Password </label>
                    <input type="password" className='bg-slate-100 text-black rounded p-2 ' placeholder='Enter Password' required>
                    </input>
                </div>

                <div id='signup-button' className='flex justify-center align-middle'>
                    <button className='bg-[#a0eafe] p-3 mt-6 rounded hover:bg-[#c5f2ff] w-full'>
                        <Link href='/signup' className='text-white'>Sign up</Link>
                    </button>
                </div>

            </form>
        </div>
    </main>
  )
}
