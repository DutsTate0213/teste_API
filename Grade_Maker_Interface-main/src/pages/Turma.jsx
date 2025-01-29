import { Center, Heading } from '@chakra-ui/react'
import Api from '../service/Api'
import { useEffect } from 'react';


function Turma(){

  useEffect(() => {
    const fetchDias = async () => {
      try {
            console.log("teste")
            const resultado = await Api.get('/dia_semana')
            console.log(resultado)
          } catch (error) {
            console.log(error);
          }
        };
        fetchDias();
      }, []);
      return (
        <Center flexDirection="column">
            <Heading as="h1" className="page-title">Home</Heading>
        </Center>
    )
}

export default Turma