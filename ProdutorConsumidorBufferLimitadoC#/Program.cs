namespace BoundedBuffer
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            var capacidade = 10;
            var buffer = new List<int?>();

            var semaphore = new Semaphore(1, 1);

            var produtor = new Produtor(buffer, capacidade, semaphore);
            var consumidor = new Consumidor(buffer, semaphore);

            await Task.WhenAll(
                produtor.Inserir(1, "A"),
                produtor.Inserir(2, "B"),
                produtor.Inserir(3, "C"),
                consumidor.Consumir("D"),
                consumidor.Consumir("E"),
                consumidor.Consumir("F"));
        }
    }
}
