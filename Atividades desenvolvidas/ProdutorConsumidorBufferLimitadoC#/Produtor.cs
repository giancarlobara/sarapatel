namespace BoundedBuffer
{
    public class Produtor
    {
        private readonly List<int?> _buffer;
        private readonly int _capacidade;

        private readonly Semaphore _semaphore;

        public Produtor(List<int?> buffer, int capacidade, Semaphore semaphore)
        {
            _buffer = buffer;
            _capacidade = capacidade;
            _semaphore = semaphore;
        }

        public async Task Inserir(int dado, string nome)
        {
            await Task.Delay(1);

            _semaphore.WaitOne();

            // Não inserir quando está cheio
            if (_buffer.Count == _capacidade)
            {
                return;
            }

            _buffer.Add(dado);

            _semaphore.Release();

            Console.WriteLine($"Produtor {nome} inseriu {dado} {DateTime.Now.Ticks}");
        }
    }
}
