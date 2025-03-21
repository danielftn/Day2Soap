"use client"

import Link from "next/link"
import React from 'react'
import { useState } from 'react';
import { useUser } from '@/context/UserContext';
import RecommendationResults from "./recommendationResults";

export default function Main() {
    const { user } = useUser();
    const [favouriteMovie, setFavouriteMovie] = useState('');
    const [favouriteGenre, setFavouriteGenre] = useState('');
    const [favouriteActor, setFavouriteActor] = useState('');
    const [favouriteDirector, setFavouriteDirector] = useState('');
    const [mpaRating, setMpaRating] = useState('G - General Audience');
    const [productionDecade, setProductionDecade] = useState('< 1980s');
    const [productionLength, setProductionLength] = useState('1h');
    const [showRecommendation, setShowRecommendation] = useState(false);
    const [recommendationData, setRecommendationData] = useState([]);
    
    const updateRecommendationData = (data) => {
        setRecommendationData(data);
    }
    const updateMovie = (event) => {
        setFavouriteMovie(event.target.value);
    };
    
    const updateGenre = (event) => {
        setFavouriteGenre(event.target.value);
    };
    
    const updateActor = (event) => {
        setFavouriteActor(event.target.value);
    };
    
    const updateDirector = (event) => {
        setFavouriteDirector(event.target.value);
    };
    
    const updateMpaRating = (event) => {
        setMpaRating(event.target.value);
    };
    
    const updateProductionDecade = (event) => {
        setProductionDecade(event.target.value);
    };
    
    const updateProductionLength = (event) => {
        setProductionLength(event.target.value);
        console.log(productionLength)
    };
    
    const handleRecommendation = async (event) => {
        event.preventDefault();
        console.log(favouriteActor, favouriteDirector, favouriteGenre, favouriteMovie, mpaRating, productionDecade, productionLength);
        // format of post request which contains movies to add to database
        const recommendationPostRequest = {
            method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    user: user ? {username: user} : {username: null}, 
                    favouriteMovie: favouriteMovie,
                    favouriteGenre: favouriteGenre,
                    favouriteActor: favouriteActor,
                    favouriteDirector: favouriteDirector,
                    mpaRating: mpaRating,
                    productionDecade: productionDecade,
                    productionLength: productionLength
                })
        }

        try{
            // call backend api
            const post_response = await fetch('https://day2soap-production.up.railway.app/api/recommendation', recommendationPostRequest);
            // if response is received then save the response as a json then change the useState for the recommendation data and then print to console
            if(post_response.ok){
                alert('Recommendation generated');
                const data = await post_response.json();
                console.log(data);
                updateRecommendationData(data);
                setShowRecommendation(true);
                console.log("Recommendation data:" + recommendationData);
            } else {
                alert('Recommendation failed');
            }
        } catch (error) {
            console.log(error)
        }
    }

    // sets the mandatory fields to null whenever you want to reset the form, also has default values for rating, production time and length
    const ResetPreferences = () => {
        setFavouriteMovie('');
        setFavouriteGenre('');
        setFavouriteActor('');
        setFavouriteDirector('');
        setMpaRating('G - General Audience');
        setProductionDecade('< 1980s');
        setProductionLength('1h');
    }

  return (
    <main className='items-center sm:m-[4rem] m-[2rem] '>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w'>
            <h2 className='text-left text-black text-[1.7rem] mb-5'>Movie Information</h2>
            <form className='m-auto' onSubmit={handleRecommendation}>
                <div id='favouritemovie' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Movie</label>
                    <input onChange={updateMovie} value={favouriteMovie  || ''} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Movie' required>
                    </input>
                </div>

                <div id='favouritegenre' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Genre </label>
                    <input onChange={updateGenre} value={favouriteGenre  || ''}type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Genre' required>
                    </input>
                </div>

                <div id='favouriteactor' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Actor (Optional) </label>
                    <input onChange={updateActor} value={favouriteActor  || ''} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Actor' >
                    </input>
                </div>
                
                <div id='favouritedirector' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Director (Optional) </label>
                    <input onChange={updateDirector} value={favouriteDirector || ''}type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Director' >
                    </input>
                </div>

                <div className='lg:grid lg:grid-cols-3 mb-5 sm:grid-cols-1'>
                    <div id="mparating" className='grid mb-5'>
                        <label className="text-black">MPA Rating</label>
                        <select onChange={updateMpaRating} value={mpaRating} className='bg-slate-100 text-black rounded p-2 w-full'>
                            <option>G - General Audience</option>
                            <option>PG - Parental Guidance Suggested</option>
                            <option>PG-13 - Parental Strongly Cautioned</option>
                            <option>R - Restricted</option>
                        </select>
                    </div>

                    <div id="productiondecade" className='grid mb-5 lg:ml-4 '>
                        <label className="text-black">Production Decade</label>
                        <select onChange={updateProductionDecade} value={productionDecade} className='bg-slate-100 text-black rounded p-2 w-full'>
                            <option>&lt; 1980s</option>
                            <option>1990s</option>
                            <option>2000s</option>
                            <option>2010s</option>
                            <option>2020s</option>
                        </select>
                    </div>

                    <div id="productiondecade" className='grid mb-5 lg:ml-4'>
                        <label className="text-black">Production Length</label>
                        <select onChange={updateProductionLength} value={productionLength} className='bg-slate-100 text-black rounded p-2 w-full'>
                            <option>1h</option>
                            <option>2h</option>
                            <option>&gt; 3h</option>
                        </select>
                    </div>
                </div>
                
                <div id='button' className='flex justify-center align-middle'>
                    <button className='bg-[#a0eafe] p-3 rounded hover:bg-[#c5f2ff] w-full'>
                        <Link href='/main' className='text-white font-bold'>Generate Movie Recommendation</Link>
                    </button>
                </div>
            </form>

                <div>
                    <button onClick={() => ResetPreferences()} className='bg-[#a0eafe] mt-3 p-3 rounded hover:bg-[#c5f2ff] w-full'>
                        <p className='text-white font-bold'>Reset Preferences</p>
                    </button>
                </div>
        </div>

        <div>
            {showRecommendation ? <RecommendationResults recommendation={recommendationData}/> : <></>}
        </div>
    </main>
  )
}
