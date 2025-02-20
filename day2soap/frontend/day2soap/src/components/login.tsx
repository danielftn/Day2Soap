"use client"

import React from 'react'
import Link from 'next/link'

export default function Login() {
  return (
    <main className='flex justify-center align-middle items-center m-[8rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w-md'>
            <h2 className='text-center text-black text-[1.7rem] mb-5'>Login</h2>
            <form className='m-auto'>
                <div id='username' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>User Name </label>
                    <input type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Enter Username'>
                    </input>
                </div>

                <div id='username' className='grid'>
                    <label className='text-black'>Password </label>
                    <input type="password" className='bg-slate-100 text-black rounded p-2 ' placeholder='Enter Password'>
                    </input>
                </div>

            </form>
        </div>
    </main>
  )
}
