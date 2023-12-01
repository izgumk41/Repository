import { getSession, signOut } from "next-auth/react";
import { NextPageContext } from "next";
import  NavBar  from "@/components/NavBar";
import Billboard from "@/components/Billboard";
import MovieList from "@/components/MovieList";
import useMovieList from "@/hooks/useMovieList";
import useFavourites from "@/hooks/useFavourites";

export async function getServerSideProps(context: NextPageContext) {
  const session = await getSession(context);

  if(!session) {
    return{
      redirect: {
        destination: "/auth",
        premanent: false,
      }
    }
  }

  return {
    props: {}
  }
}

export default function Home() {
  const {data: movies = []} = useMovieList();
  const {data: favourites = []} = useFavourites();

  return (
    <>
      <NavBar />
      <Billboard />
      <div className="pb-40">
      <MovieList title="Trending Now" data={movies} />
      <MovieList title="My List" data={favourites} />
      </div>
    </>

  )
}
