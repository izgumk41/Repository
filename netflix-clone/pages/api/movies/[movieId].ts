import prismadb from '@/lib/prismadb';
import { NextApiRequest, NextApiResponse } from "next";

import serverAuth from '@/lib/serverAuth';

export default async function handler(req: NextApiRequest, res:NextApiResponse) {
    if(req.method !== 'GET') {
        console.log("doneBBB");
        return res.status(405).end();
        
    }

    try
    {
        await serverAuth(req);

        const{ movieId } = req.query;
        console.log("MOVIE ID");
        console.log(movieId);

        if(typeof movieId !== "string" ){
            throw new Error('Invalid ID')
        }

        if(!movieId){
            throw new Error('Invalid ID');
        }

        const movies = await prismadb.movie.findUnique({
            where:{
                id: movieId
            }
        });
    
        if(!movies){
            throw new Error('Invalid ID');
        }
        
        console.log(movies);
        return res.status(200).json(movies);

    }catch(error){
        console.log(error);
        return res.status(400).end();
    }
}