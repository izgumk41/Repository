import useSwr from 'swr';
import fetcher from '@/lib/fetcher';

const useMovie = (id?: string) => {
    const { data, error, isLoading } = useSwr(id ? `/api/movies/${id}` : "655f30493579a857a3838f2e", fetcher, {
        revalidateIfStale: false,
        revalidateOnFocus: false,
        revalidateOnReconnect: false
    });

    return {
        data,
        error,
        isLoading,
    }
}

export default useMovie;