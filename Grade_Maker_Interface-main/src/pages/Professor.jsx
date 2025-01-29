import React, { useEffect, useState } from "react";
import "../styles/global.css";
import { Heading, Box, Flex, Text, Circle, Button } from "@chakra-ui/react";
import { getProfessor, getProfessorDisciplina } from "../service/ProfessorService";
import FormProfessor from "../components/forms/formProfessor/FormProfessor"; // Certifique-se de importar o FormProfessor 
import ViewProfessor from "../components/forms/formProfessor/ViewProfessor";
const Professor = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [professores, setProfessores] = useState([]);
  const [selectedProfessor, setSelectedProfessor] = useState(null);

  const openView = (professor) => {
    setSelectedProfessor(professor);
    setIsModalOpen(true);
  };

  const closeView = () => {
    setIsModalOpen(false);
    setSelectedProfessor(null);
  };

  const openForm = () => {
    setIsFormOpen(true);
  };

  const closeForm = () => {
    setIsFormOpen(false);
  };

  useEffect(() => {
    const fetchProfessores = async () => {
      try {
        const response = await getProfessor();
        console.log(response)
        setProfessores(response);
      } catch (error) {
        console.log(error);
      }
    };
    fetchProfessores();
  }, []);

  return (
    <div className="page-container">
      <div className="content-wrapper">
        <Heading
          className="page-title"
          as="h2"
          fontSize="2xl"
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
          Professores
        </Heading>
        <Button 
          onClick={openForm}
          backgroundColor="purple.500"
          color="white"
          borderRadius="lg"
          mb={10}
          _hover={{
            backgroundColor: "purple.600",
            cursor: "pointer",
          }}
        >Adicionar Professor</Button>
        <Flex justify="center" align="center" p={5}>
          <Flex
            wrap="wrap"
            maxW="1000px"
            marginBottom={10}
            justify="center"
            gap={4}
            rowGap={20}
          >
            {professores.map((professor, index) => (
              <Button
                key={index}
                as="div"
                variant="unstyled"
                w={{ base: "100%", sm: "48%", md: "45%", lg: "30%" }}
                mb={4}
                onClick={() => openView(professor)}
                _hover={{
                  backgroundColor: "purple.600",
                  cursor: "pointer",
                }}
              >
                <Box
                  bg="purple.800"
                  color="white"
                  p={5}
                  borderRadius="lg"
                  display="flex"
                  alignItems="center"
                  h="auto"
                  overflow="hidden"
                >
                  <Circle size="50px" bg="purple.500" mr={4}>
                    <Text
                      fontSize={{ base: "sm", md: "md", lg: "lg" }}
                      fontWeight="bold"
                    >
                      {professor.nome[0] + professor.nome[1]}
                    </Text>
                  </Circle>
                  <Box>
                    <Text
                      fontSize={{ base: "md", md: "lg", lg: "xl" }}
                      fontWeight="bold"
                      maxW="200px"
                      whiteSpace="nowrap"
                      overflow="hidden"
                      textOverflow="ellipsis"
                      display="inline-block"
                    >
                      {professor.nome}
                    </Text>
                    <Text
                      fontSize={{ base: "xs", md: "sm", lg: "md" }}
                      maxW="200px"
                      whiteSpace="nowrap"
                      overflow="hidden"
                      textOverflow="ellipsis"
                      display="inline-block"
                    >
                      {professor.type}
                    </Text>
                  </Box>
                </Box>
              </Button>
            ))}
          </Flex>
        </Flex>

        {/* Modal FormProfessor */}
        <ViewProfessor isOpen={isModalOpen} onClose={closeView} professor={selectedProfessor} />
        <FormProfessor isOpen={isFormOpen} onClose={closeForm} />
      </div>
    </div>
  );
};

export default Professor;
