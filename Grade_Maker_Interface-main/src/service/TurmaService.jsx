import Api from "./Api";

export const getTurma = async () => {
  try {
    const resposta = await Api.get("/turma");
    if (resposta.data && Array.isArray(resposta.data)) {
      return resposta.data;
    } else {
      throw new Error("Formato de dados inesperado");
    }
  } catch (erro) {
    console.error("Erro ao buscar as turmas:", erro.message);
    throw erro;
  }
};

export const insertTurma = async (objectTurma) => {
  try {
    const resposta = await Api.post("/turma", objectTurma);
    return resposta;
  } catch (erro) {
    console.error("Erro ao inserir turma:", erro);
    throw erro;
  }
};

export const updateTurma = async (id, objectTurma) => {
  try {
    const resposta = await Api.put(`/turma/${id}`, objectTurma);
    return resposta;
  } catch (erro) {
    console.error("Erro ao atualizar turma:", erro);
    throw erro;
  }
};

export const deleteTurma = async (id) => {
  try {
    const resposta = await Api.delete(`/turma/${id}`);
    return resposta;
  } catch (erro) {
    console.error("Erro ao excluir turma:", erro);
    throw erro;
  }
};