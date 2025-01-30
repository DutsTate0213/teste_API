import React, { useEffect } from 'react';
import { Heading } from '@chakra-ui/react'
import { getTurma } from "../service/TurmaService";



function Turma(){

  useEffect(() => {

      }, []);
      return (
          <div className="page-container">
          <div className="content-wrapper">
              <Heading
                className="page-title"
                as="h2"
                fontSize="4xl"
                position="relative"
                textAlign="center"
                _after={{
                  content: '""',
                  display: "block",
                  width: "100%",
                  height: "7px",
                  backgroundColor: "purple.500",
                  position: "absolute",
                  bottom: "-5px",
                  left: 0,
                }}
              >
                Turma
              </Heading>
          </div>
      </div>
    )
}

export default Turma