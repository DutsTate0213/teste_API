import Api from "./Api";

export const getMatriz = async () => {
  try {
    const resposta = await Api.get("/matriz");
    if (resposta.data && Array.isArray(resposta.data)) {
      return resposta.data;
    } else {
      throw new Error("Formato de dados inesperado");
    }
  } catch (erro) {
    console.error("Erro ao buscar as matrizes:", erro.message);
    throw erro;
  }
};

export const insertMatriz = async (objectMatriz) => {
  try {
    const resposta = await Api.post("/matriz", objectMatriz);
    return resposta;
  } catch (erro) {
    console.error("Erro ao inserir matriz:", erro);
    throw erro;
  }
};

export const updateMatriz = async (id, objectMatriz) => {
  try {
    const resposta = await Api.put(`/matriz/${id}`, objectMatriz);
    return resposta;
  } catch (erro) {
    console.error("Erro ao atualizar matriz:", erro);
    throw erro;
  }
};

export const deleteMatriz = async (id) => {
  try {
    const resposta = await Api.delete(`/matriz/${id}`);
    return resposta;
  } catch (erro) {
    console.error("Erro ao excluir matriz:", erro);
    throw erro;
  }
};